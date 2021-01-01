# Stage 0, "build-stage", based on Node.js to build the frontend
FROM node:alpine as build
WORKDIR /app
COPY package*.json /app/
RUN npm install
COPY . /app/
RUN npm run build

# Stage 1, based on NGINX to provide a configuration to be used with react-router
FROM nginx:1.15.2-alpine
COPY --from=build /app/build /var/www
COPY nginx/nginx.conf /etc/nginx/nginx.conf
ENTRYPOINT ["nginx","-g","daemon off;"]