# Build stage
FROM node:20 AS build
WORKDIR /app
COPY . .
RUN npm install
RUN npm run build --prod

# Serve stage
FROM nginx:alpine
COPY --from=build /app/dist/your-app-name /usr/share/nginx/html
