package com.jey.demo.entities;

import javax.persistence.Entity;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Log extends BaseEntity {

	private static final long serialVersionUID = -8625988394590970421L;

	private String ip;

	private String apiName;

	private String data;
}
