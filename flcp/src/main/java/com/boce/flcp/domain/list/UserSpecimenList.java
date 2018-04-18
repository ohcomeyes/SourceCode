package com.boce.flcp.domain.list;

import com.boce.flcp.domain.UserSpecimen;

import java.util.List;

/**
 * @author xutang
 * @version V1.0
 * @Package com.boce.flcp.domain.list
 * @Description: TODO(打板师)
 * @date 2017/12/12 14:05
 */
public class UserSpecimenList {
     private int code;
     private String msg;
     private int count;
     private List<UserSpecimen> data;

     public int getCode() {
          return code;
     }

     public void setCode(int code) {
          this.code = code;
     }

     public String getMsg() {
          return msg;
     }

     public void setMsg(String msg) {
          this.msg = msg;
     }

     public int getCount() {
          return count;
     }

     public void setCount(int count) {
          this.count = count;
     }

     public List<UserSpecimen> getData() {
          return data;
     }

     public void setData(List<UserSpecimen> data) {
          this.data = data;
     }
}
