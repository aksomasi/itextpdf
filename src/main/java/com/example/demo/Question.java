package com.example.demo;

public class Question {

	private String question;
	private String comments;
	private String answer;

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public Question(String question, String comments, String answer) {
		super();
		this.question = question;
		this.comments = comments;
		this.answer = answer;
	}

}
