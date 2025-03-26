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
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
@TableName("clock")
public class Clock implements Serializable {
	private static final long serialVersionUID = 1L;
	@TableId
	private Long id;
	private String name;
	private String tw;
	private String phone;
	private String szd;
	private String stzk;
	private String grjc;
	private String bz;
	private Date  createTime;
//	CREATE TABLE `NewTable` (
//			`id`  int(11) NULL DEFAULT NULL ,
//           `name`  varchar(255)  '姓名' ,
//			`phone`  varchar(255)  '手机号' ,
//			`szd`  varchar(255)  '所在地' ,
//			`stzk`  varchar(255)  '身体状况' ,
//			`grjc`  varchar(255)  '是否和感染人员接触' ,
//			`bz`  varchar(255) COLLATE utf8_general_ci NULL DEFAULT NULL ,
//`create_time`  datetime NULL DEFAULT NULL COMMENT '日期'
	;


}
