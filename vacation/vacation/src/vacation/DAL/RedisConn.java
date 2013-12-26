package vacation.DAL;

import java.sql.Connection;
import java.util.Calendar;

import org.json.JSONException;





import redis.clients.jedis.Jedis;


public class RedisConn
{
	static Jedis conn;
	
	private static void initConn() throws ClassNotFoundException, JSONException
	{
		conn= new Jedis("localhost");

		AddDummyData(conn);
		
		
        System.out.println((new StringBuilder("conn successed. conn=")).append(conn).toString());
    }
	
	
    private static void AddDummyData(Jedis jconn) {
		// TODO Auto-generated method stub	
		Calendar now = Calendar.getInstance();
		double userLogInTime = (double)now.get(Calendar.MINUTE);
		System.out.println("userLogInTime="+userLogInTime);
		jconn.zadd("users:on:line",userLogInTime, "1");
		jconn.zadd("users:on:line",userLogInTime, "2");

		jconn.set("uid:1:username","Lior1989");
		jconn.set("uid:1:password","123456");
		jconn.set("uid:1:reg_date","2013-01-12");
		jconn.set("uid:1:firstname","Lior");
		jconn.set("uid:1:lastname","Yaffe");
		jconn.set("uid:1:address","Bialik 96 Ramat-Gan");
		jconn.set("uid:1:phone","0501234567");
		jconn.set("uid:1:email","lieo@walla.co.il");
		jconn.lpush("uid:1:orders","1");
		
		jconn.set("uid:2:username","LiorMa");
		jconn.set("uid:2:password","Aa123456");
		jconn.set("uid:2:reg_date","2013-01-12");
		jconn.set("uid:2:firstname","Lior");
		jconn.set("uid:2:lastname","Matityahu");
		jconn.set("uid:2:address","Petach-Tikva");
		jconn.set("uid:2:phone","05098721234");
		jconn.set("uid:2:email","lala@gmail.com");
		jconn.lpush("uid:2:orders","2");
		
		// Orders
		jconn.set("orderid:1:userid","1");
		jconn.set("orderid:1:methodid","3");
		jconn.set("orderid:1:date","2013-30-11");
		
		jconn.set("orderid:2:userid","2");
		jconn.set("orderid:2:methodid","1");
		jconn.set("orderid:2:date","2013-03-12");
		
		
		//insert into orders values(1,1,3,STR_TO_DATE('30-11-2013', '%d-%m-%Y'));
		//insert into orders values(2,2,1,STR_TO_DATE('3-12-2013', '%d-%m-%Y'));

		
	}

	public static Jedis getConnection() throws ClassNotFoundException, JSONException
    {
    	conn = null;
    	if (conn == null)
    	{
    		initConn();
    	}
    	
    	return conn;
    }

}
