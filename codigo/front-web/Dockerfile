# Imagem do NodeJs
FROM node:18.12.1-alpine

# Criação do diretório da aplicação
RUN mkdir -p /usr/src
WORKDIR /usr/src

# Copiando arquivos do código fonte
COPY . /usr/src

# instalando dependencias
RUN npm install

# Iniciando a aplicação🔥
RUN npm run build
EXPOSE 3000
CMD npm run start