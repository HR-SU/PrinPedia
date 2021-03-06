package com.prinpedia.backend.entity;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.util.List;

@Document(collection = "entry")
public class Entry {
    @MongoId
    ObjectId entryId;
    Integer index;
    List<Content> content;
    String summary;
    @Indexed(unique = true)
    String title;
    List<Section> sectionList;
    String wikiText;
    Boolean locked;

    public ObjectId getEntryId() {
        return entryId;
    }
    public void setEntryId(ObjectId entryId) {
        this.entryId = entryId;
    }

    public Integer getIndex() {
        return index;
    }
    public void setIndex(Integer index) {
        this.index = index;
    }

    public List<Content> getContent() {
        return content;
    }
    public void setContent(List<Content> content) {
        this.content = content;
    }

    public String getSummary() {
        return summary;
    }
    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getTitle() { return title; }
    public void setTitle(String title) {
        this.title = title;
    }

    public List<Section> getSectionList() {
        return sectionList;
    }
    public void setSectionList(List<Section> sectionList) {
        this.sectionList = sectionList;
    }

    public String getWikiText() {
        return wikiText;
    }
    public void setWikiText(String wikiText) {
        this.wikiText = wikiText;
    }

    public Boolean getLocked() {
        return locked;
    }
    public void setLocked(Boolean locked) {
        this.locked = locked;
    }
}
