package com.usthb.client;

import java.io.Serializable;

import com.usthb.modeles.Abonné;

public class AnswerObject implements Serializable {
	private static final long serialVersionUID = -7176331492159635758L;
	private boolean answer=false;
	private Abonné abonné=null;

	public Abonné getAbonné() {
		return abonné;
	}

	public void setAbonné(Abonné abonné) {
		this.abonné = abonné;
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

	public AnswerObject(Abonné abonné) {
		this.abonné = abonné;
	}

}
