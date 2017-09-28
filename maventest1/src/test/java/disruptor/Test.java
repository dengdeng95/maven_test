package disruptor;

import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String hashalgorithmName = "md5";
		String source = "123456";
		ByteSource salt = ByteSource.Util.bytes("user1");
		int hashIterations = 2;
		System.out.println(new SimpleHash(hashalgorithmName, source, salt, hashIterations));
	}

}
