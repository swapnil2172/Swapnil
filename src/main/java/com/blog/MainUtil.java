package com.blog;

import jakarta.persistence.criteria.CriteriaBuilder;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MainUtil {
    public static void main(String[] args) {

        MainUtil mainUtil=new MainUtil();

        Posti p1=new Posti();
        p1.setId(1);
        p1.setTitle("swapnil");
        p1.setContent("swap");

        Posti p2=new Posti();
        p2.setId(2);
        p2.setTitle("sachin");
        p2.setContent("sac");

        Posti p3=new Posti();
        p3.setId(3);
        p3.setTitle("sanjay");
        p3.setContent("san");

        List<Posti> data=Arrays.asList(p1,p2,p3);
        List<PostDtoi> newdata = data.stream().map(mainUtil::maptoDto).collect(Collectors.toList());
        System.out.println(newdata);
    }

   PostDtoi maptoDto(Posti posti){
        PostDtoi postDtoi=new PostDtoi();
        postDtoi.setId(posti.getId());
        postDtoi.setTitle(posti.getTitle());
        postDtoi.setContent(posti.getContent());

        return  postDtoi;
    }

}
