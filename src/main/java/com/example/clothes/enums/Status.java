package com.example.clothes.enums;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Getter
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public enum Status {
    ACTIVE("ACTIVE"){
        @Override
        public String getRussian(){
            return "Активный";
        }
    },
    DELETED("DELETED"){
        @Override
        public String getRussian(){
            return "Удален";
        }
    },
    ARCHIVED("ARCHIVED"){
        @Override
        public String getRussian(){
            return "Архивирован";
        }
    },
    BLACKLIST("BLACKLIST"){
        @Override
        public String getRussian(){
            return "В черном списке";
        }
    },
    FROZEN("FROZEN"){
        @Override
        public String getRussian(){
            return "Заморожен";
        }
    };

    final String name;
    public String getRussian() {return "Статус";}
}