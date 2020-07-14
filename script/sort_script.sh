#curl -v "http://localhost:8080/book/list?sortBy=price&asc=false"
curl -u "shopper1:password" "localhost:8080/api/book/list?sortby=title&asc=true"