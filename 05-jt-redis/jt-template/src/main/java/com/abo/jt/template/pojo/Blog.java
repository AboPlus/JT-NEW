package com.abo.jt.template.pojo;

import java.io.Serializable;

/**
 * @author Abo
 */
public class Blog implements Serializable {
    private static final long serialVersionUID = -7863254569014980913L;
    private Integer id;
    private String title;

    public Blog(){
        System.out.println("无参构造");
    }
    public Blog(Integer id,String title){
        System.out.println("含参构造");
        this.id = id;
        this.title = title;
    }


    @Override
    public String toString() {
        return "Blog{" +
                "id=" + id +
                ", title='" + title + '\'' +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
