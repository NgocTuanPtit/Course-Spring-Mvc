package com.tony_funny.Ultils;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

@Component
public class MessageUltils {

	public static Map<String, String> getMessage(String message){
		Map<String , String> messageMap = new HashMap<String, String>();
		if(message.equals("insert_success")) {
			messageMap.put("message", "insert_success");
			messageMap.put("alert", "success");
		}else {
			if(message.equals("insert_error")) {
				messageMap.put("message", "insert_error");
				messageMap.put("alert", "danger");
			}else {
				if(message.equals("update_success")) {
					messageMap.put("message", "update_success");
					messageMap.put("alert", "success");
				}else {
					if(message.equals("update_error")) {
						messageMap.put("message", "update_error");
						messageMap.put("alert", "danger");
					}else {
						if(message.equals("delete_success")) {
							messageMap.put("message", "delete_success");
							messageMap.put("alert", "success");
						}else {
							if(message.equals("delete_error")) {
								messageMap.put("message", "delete_error");
								messageMap.put("alert", "danger");
							}
						}
					}
				}
			}
		}
		return messageMap;
	}
}
