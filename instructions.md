docker-compose up -d
docker exec -it control2-postgres-1 psql -U user -
d database SELECT * FROM table;
docker-compose up -d SELECT * FROM table;