package com.springchallenge.firstspringapp.models;

import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "CTF")
public class Animes {
    @Id
    public ObjectId _id;
    public int anime_id;
    public String name;
    public String genre;
    public String type;
    public int episodes;
    public double rating;
    public String img;
    public String studios;
    public String source;
    public String main_cast;
    public int c1;
    public int c2 ;
    public int members;
}
