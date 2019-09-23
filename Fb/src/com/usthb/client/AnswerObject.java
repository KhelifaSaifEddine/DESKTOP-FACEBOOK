package com.usthb.client;

import java.io.Serializable;

import com.usthb.modeles.Abonn�;

public class AnswerObject implements Serializable {
	private static final long serialVersionUID = -7176331492159635758L;
	private boolean answer=false;
	private Abonn� abonn�=null;

	public Abonn� getAbonn�() {
		return abonn�;
	}

	public void setAbonn�(Abonn� abonn�) {
		this.abonn� = abonn�;
	}

	public boolean isAnswer() {
		return answer;
	}

	public void setAnswer(boolean answer) {
		this.answer = answer;
	}

	public AnswerObject(boolean answer) {
		this.answer = answer;
	}

	public AnswerObject(Abonn� abonn�) {
		this.abonn� = abonn�;
	}

}
