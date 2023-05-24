# Use a base image, por exemplo, o PostgreSQL
FROM postgres:14.1

# Defina as variáveis de ambiente para a configuração do banco de dados
ENV POSTGRES_PASSWORD=PstD2a5DA4fUvg
ENV POSTGRES_USER=postgres
ENV POSTGRES_DB=avaliai

# Exponha a porta necessária para acessar o banco de dados
EXPOSE 5432

# Copie os volumes para o diretório correspondente
VOLUME /var/lib/postgresql
VOLUME /var/lib/postgresql/data

# Comando para iniciar o servidor do banco de dados quando o contêiner for iniciado
CMD ["postgres"]