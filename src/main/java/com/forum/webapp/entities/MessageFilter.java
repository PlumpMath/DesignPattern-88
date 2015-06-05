package com.forum.webapp.entities;

public class MessageFilter {

    private String authorToContain;

    private String contentToContain;

    public String getAuthorToContain() {
        return authorToContain;
    }

    public void setAuthorToContain(String authorToContain) {
        this.authorToContain = authorToContain;
    }

    public String getContentToContain() {
        return contentToContain;
    }

    public void setContentToContain(String contentToContain) {
        this.contentToContain = contentToContain;
    }

}
