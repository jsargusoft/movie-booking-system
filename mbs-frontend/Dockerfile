FROM node:20

WORKDIR /mbs-frontend

COPY package*.json ./

COPY . .

RUN npm install -g @angular/cli

RUN npm install

RUN npm run build 

EXPOSE 4350

CMD ["ng", "serve", "--host", "0.0.0.0", "--poll", "2000"]


