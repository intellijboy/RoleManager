package cn.edu.jxufe.junit;

import java.util.Scanner;

import com.alibaba.fastjson.JSON;

import cn.edu.jxufe.entity.User;

public class TestEnvirm {
		public static void main(String[] args) {
			User user = generateUserEntity();
			System.out.println(JSON.toJSON(user));
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
