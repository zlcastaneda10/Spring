package com.springchallenge.firstspringapp.models;

import lombok.Data;
import org.bson.types.ObjectId;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@Document(collection = "CTF")
public class Animes {
/*    @Id
    public ObjectId _id;*/
    public int anime_id;
    public String name;
    public List<String> genre;
    public String type;
    public int episodes;
    public double rating;
    public List<String> studios;
    public String source;
    public List<String> main_cast;
}
