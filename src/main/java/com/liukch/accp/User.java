package com.liukch.accp;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * @author liukch on 2019-11-14
 */
@Setter
@Getter
@AllArgsConstructor
public class User {
    long id;

    String name;

    int avatarId;

    int level;

    int exp;
}
