/**
 * .
 *
 * Thm
 *
 * ！
 */

package io.renren.modules.sys.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
@TableName("go_out")
public class GoOut implements Serializable {
	private static final long serialVersionUID = 1L;
	@TableId
	private Long id;
	@JsonFormat(pattern="yyyy-MM-dd")
	private Date wcTime;
	private String wcYy;
	private String wcDd;
	private String username;
	private String phone;
	private String bz;
	private String stu;



//			`id`  int(11) NOT NULL AUTO_INCREMENT ,
//`wc_time`  datetime NULL DEFAULT NULL COMMENT '外出时间' ,
//			`wc_yy`  varchar(255)Thm NULL DEFAULT NULL COMMENT '外出原因' ,
//			`bz`  varchar(255)Thm NULL DEFAULT NULL COMMENT '备注说明' ,
//			`username`  varchar(255)Thm NULL DEFAULT NULL COMMENT '用户姓名' ,
//			`phone`  varchar(255)Thm NULL DEFAULT NULL COMMENT '联系手机号码' ,
//	;



}
