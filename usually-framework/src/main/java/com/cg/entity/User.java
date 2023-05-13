package com.cg.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * (Login)表实体类
 *
 * @author makejava
 * @since 2023-05-06 14:45:57
 */
@SuppressWarnings("serial")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    
    private Integer id;
    
    private String name;
    
    private String password;


    
    }

