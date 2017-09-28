package disruptor;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import com.lmax.disruptor.EventFactory;
import com.lmax.disruptor.EventHandler;
import com.lmax.disruptor.EventTranslatorOneArg;
import com.lmax.disruptor.IgnoreExceptionHandler;
import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.WorkHandler;
import com.lmax.disruptor.YieldingWaitStrategy;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.dsl.EventHandlerGroup;
import com.lmax.disruptor.dsl.ProducerType;

public class Demo1 {
	public static void main(String[] args) throws InterruptedException {
		/*ExecutorService executorService = Executors.newFixedThreadPool(2);

        int bufferSize = 1024;

        Disruptor<MyEvent> disruptor = new Disruptor<MyEvent>(new MyEventFactory(),
                bufferSize, executorService, ProducerType.SINGLE, new YieldingWaitStrategy());
        disruptor.handleExceptionsWith(new IgnoreExceptionHandler());

        disruptor.handleEventsWith(new MyEventHandler(),new MyEventHandler());
        disruptor.handleEventsWith(new MyEventHandler()).then(new MyEventHandler());
        EventHandlerGroup<MyEvent> handlerGroup = disruptor.handleEventsWith(new MyEventHandler());  //Pipeline
        handlerGroup.then(new MyEventHandler());
        RingBuffer<MyEvent> ringBuffer = disruptor.start();

        MyEventProducer producer = new MyEventProducer(ringBuffer);
        for (long i = 0; i < 10; i++) {
            producer.onData(i);
            Thread.sleep(1000);// wait for task execute....
        }

        disruptor.shutdown();

        ExecutorsUtils.shutdownAndAwaitTermination(executorService, 60, TimeUnit.SECONDS);*/
        
        
        ExecutorService executorService = Executors.newFixedThreadPool(2);

        int bufferSize = 1024;

        Disruptor<MyEvent> disruptor = new Disruptor<MyEvent>(new MyEventFactory(),
                bufferSize, executorService, ProducerType.SINGLE, new YieldingWaitStrategy());
        disruptor.handleExceptionsWith(new IgnoreExceptionHandler());
        disruptor.handleEventsWithWorkerPool(new MyEventWorkHandler("worker-1"),new MyEventWorkHandler("worker-2"));
        RingBuffer<MyEvent> ringBuffer = disruptor.start();

        MyEventProducer producer = new MyEventProducer(ringBuffer);
        for (long i = 0; i < 10; i++) {
            producer.onData(i);
            Thread.sleep(1000);// wait for task execute....
        }

        disruptor.shutdown();
	}
}
class MyEvent{
	private long value;

    public void setValue(long value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "MyEvent{" +
                "value=" + value +
                '}';
    }
}
//
class MyEventFactory implements EventFactory<MyEvent> {
    @Override
    public MyEvent newInstance() {
        return new MyEvent();
    }
}
//生产者类
class MyEventProducer {

    private RingBuffer<MyEvent> ringBuffer;

    public MyEventProducer(RingBuffer<MyEvent> ringBuffer) {
        this.ringBuffer = ringBuffer;
    }

    private static final EventTranslatorOneArg TRANSLATOR = new EventTranslatorOneArg<MyEvent, Long>() {

        @Override
        public void translateTo(MyEvent event, long sequence, Long value) {
            event.setValue(value);
        }
    };
    
    public void onData(final Long value) {
        ringBuffer.publishEvent(TRANSLATOR,value);
    }
}
//处理消息的地方
class MyEventHandler implements EventHandler<MyEvent> {
    @Override
    public void onEvent(MyEvent event, long sequence, boolean endOfBatch) throws Exception {
        System.out.println(event);
    }
}
//ExecutorsUtils就是写的一个关闭ExecutorService的方法
class ExecutorsUtils {

    public static  void shutdownAndAwaitTermination(ExecutorService pool,int timeout,TimeUnit unit) {
        pool.shutdown(); // Disable new tasks from being submitted
        try {
            // Wait a while for existing tasks to terminate
            if (!pool.awaitTermination(timeout/2, unit)) {
                pool.shutdownNow(); // Cancel currently executing tasks
                // Wait a while for tasks to respond to being cancelled
                if (!pool.awaitTermination(timeout/2, unit))
                    System.err.println("Pool did not terminate");
            }
        } catch (InterruptedException ie) {
            // (Re-)Cancel if current thread also interrupted
            pool.shutdownNow();
            // Preserve interrupt status
            Thread.currentThread().interrupt();
        }
    }
}
class MyEventWorkHandler implements WorkHandler<MyEvent> {

    private String workerName;

    public MyEventWorkHandler(String workerName) {
        this.workerName = workerName;
    }

    @Override
    public void onEvent(MyEvent event) throws Exception {
        System.out.println(workerName + " handle event:" + event);
    }
}