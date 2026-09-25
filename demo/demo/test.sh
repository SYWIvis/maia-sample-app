#!/bin/bash

BASE_URL="http://localhost:8080/api/products"
USER_BASE_URL="http://localhost:8080/api/users"

display_all(){
    curl -s "$BASE_URL"
}
create_product(){ #新しいやつを作る関数
    local name="$1"
    local price="$2"
    curl -s -X POST "$BASE_URL" -H "Content-Type: application/json" \
      -d "{\"name\":\"$name\", \"price\": $price}"
}

update_product(){
    local id="$1"
    local name="$2"
    local price="$3"
    curl -s -X PUT "$BASE_URL/$id" -H "Content-Type: application/json" \
      -d "{\"name\":\"$name\", \"price\": $price}"
}

delete_byId(){
    local id="$1"
    curl -s -X DELETE "$BASE_URL/$id"
}

register_user(){
    local username="$1"
    local password="$2"
    curl -s -X POST "$USER_BASE_URL/register" -H "Content-Type: application/json" \
      -d "{\"username\":\"$username\", \"password\": \"$password\"}"
}

login_test(){
    local username="$1"
    local password="$2"
    curl -s -o /dev/null -w "%{http_code}\n" -u "$username:$password" "http://localhost:8080/v3/api-docs"
}

login_testv(){
    local username="$1"
    local password="$2"
    curl -s -o -v /dev/null -w "%{http_code}\n" -u "$username:$password" "http://localhost:8080/v3/api-docs"
}







