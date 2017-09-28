package disruptor;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.lmax.disruptor.BusySpinWaitStrategy;
import com.lmax.disruptor.EventFactory;
import com.lmax.disruptor.EventHandler;
import com.lmax.disruptor.EventTranslator;
import com.lmax.disruptor.IgnoreExceptionHandler;
import com.lmax.disruptor.WorkHandler;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.dsl.EventHandlerGroup;
import com.lmax.disruptor.dsl.ProducerType;

public class Demo3 {
	 public static void main(String[] args) throws InterruptedException {  
         long beginTime=System.currentTimeMillis();  
         
         //创建一个disruptor对象
         int bufferSize=1024;  
         ExecutorService executor=Executors.newFixedThreadPool(4);  
         //第一个参数用来在ring buffer中创建event，第二个参数是ring buffer的大小，第三个参数是消费者处理消息而使用的线程池。第四个参数是单或者多生产者模式，第五个参数是可选的等待策略。  
         Disruptor<TradeTransaction> disruptor=new Disruptor<TradeTransaction>(new EventFactory<TradeTransaction>() {  
             @Override  
             public TradeTransaction newInstance() {  
                 return new TradeTransaction();  
             }  
         }, bufferSize, executor, ProducerType.MULTI, new BusySpinWaitStrategy());  
           
         //使用disruptor创建消费者组C1,C2  
         disruptor.handleExceptionsWith(new IgnoreExceptionHandler());
         EventHandlerGroup<TradeTransaction> handlerGroup = disruptor.handleEventsWithWorkerPool(new TradeTransactionVasConsumer("A"),new TradeTransactionVasConsumer("B")); 
         
         /*TradeTransactionJMSNotifyHandler jmsConsumer=new TradeTransactionJMSNotifyHandler();  
         //声明在C1,C2完事之后执行JMS消息发送操作 也就是流程走到C3  
         handlerGroup.then(jmsConsumer); */ 
           
           
         disruptor.start();//启动  
         CountDownLatch latch=new CountDownLatch(1);  
         //生产者准备  
         executor.submit(new TradeTransactionPublisher(latch, disruptor));  
         latch.await();//等待生产者完事.  
         disruptor.shutdown();  
         executor.shutdown();  
           
         System.out.println("总耗时:"+(System.currentTimeMillis()-beginTime));  
     }  
}
class TradeTransactionJMSNotifyHandler implements EventHandler<TradeTransaction> {

	@Override
	public void onEvent(TradeTransaction event, long sequence, boolean endOfBatch) throws Exception {
		// TODO Auto-generated method stub
	}  
    
}  
class TradeTransactionPublisher implements Runnable{  
    Disruptor<TradeTransaction> disruptor;  
    private CountDownLatch latch;  
    private static int LOOP=20;//模拟一千万次交易的发生  
  
    public TradeTransactionPublisher(CountDownLatch latch,Disruptor<TradeTransaction> disruptor) {  
        this.disruptor=disruptor;  
        this.latch=latch;  
    }  
  
    @Override  
    public void run() {  
        TradeTransactionEventTranslator tradeTransloator=new TradeTransactionEventTranslator();  
        for(int i=0;i<LOOP;i++){  
            disruptor.publishEvent(tradeTransloator);  
        }  
        latch.countDown();
    }  
      
}  
class TradeTransactionEventTranslator implements EventTranslator<TradeTransaction>{  
    private Random random=new Random();  
    public void translateTo(TradeTransaction event, long sequence) {  
        this.generateTradeTransaction(event);  
    }  
    private TradeTransaction generateTradeTransaction(TradeTransaction trade){  
        trade.setPrice(random.nextDouble()*9999);  
        return trade;  
    }  
}  

//使用WorkHandler 实现一条消息只能被一个消费者消费
class TradeTransactionVasConsumer implements EventHandler<TradeTransaction>,WorkHandler<TradeTransaction> {  
	 private String name;
	    
	public TradeTransactionVasConsumer(String name){
		this.name = name;
	}
    @Override  
    public void onEvent(TradeTransaction event, long sequence,  
            boolean endOfBatch) throws Exception {  
        //do something....  
    }
	@Override
	public void onEvent(TradeTransaction event) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("消费者"+name+"  price="+event.getPrice());
	}  
      
}  
class TradeTransaction {  
    private String id;//交易ID  
    private double price;//交易金额  
      
    public TradeTransaction() {  
    }  
    public TradeTransaction(String id, double price) {  
        super();  
        this.id = id;  
        this.price = price;  
    }  
    public String getId() {  
        return id;  
    }  
    public void setId(String id) {  
        this.id = id;  
    }  
    public double getPrice() {  
        return price;  
    }  
    public void setPrice(double price) {  
        this.price = price;  
    }  
}
class TradeTransactionInDBHandler implements EventHandler<TradeTransaction>,WorkHandler<TradeTransaction> {  
    private String name;
    
	public TradeTransactionInDBHandler(String name){
		this.name = name;
	}
    @Override  
    public void onEvent(TradeTransaction event, long sequence,  
            boolean endOfBatch) throws Exception {  
        this.onEvent(event);  
    }  
  
    @Override  
    public void onEvent(TradeTransaction event) throws Exception {  
        //这里做具体的消费逻辑  
        event.setId(UUID.randomUUID().toString());//简单生成下ID  
        System.out.println("消费者"+name+"  price="+event.getPrice());  
    }  
}  
