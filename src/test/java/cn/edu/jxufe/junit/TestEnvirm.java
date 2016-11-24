package cn.edu.jxufe.junit;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import cn.edu.jxufe.entity.User;

public class TestEnvirm {
		public static void main(String[] args) {
			getMyList();
		}
		
		public static void getMyList(){
			List<String> list = new ArrayList<String>();
			list.add("test1");
			list.add("test2");
			list.add("test3");
			list.add("test4");
			list.add("test5");
			Iterator<String> iterator = list.iterator();
			while(iterator.hasNext()){
				System.out.println(iterator.next());
			}
		}
		
		public static User generateUserEntity(){
			Scanner sc = new Scanner(System.in);
			User user = new User();
			user.setId(sc.nextLong());
			user.setUserName(sc.next());
			user.setPassword(sc.next());
			return user;
		}
}
