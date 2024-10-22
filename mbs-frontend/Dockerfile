FROM node:20

WORKDIR /mbs-frontend

COPY . /mbs-frontend

RUN npm install -g @angular/cli

EXPOSE 4200

CMD ["ng", "serve", "--host", "0.0.0.0", "--poll", "2000"]


