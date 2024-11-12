FROM node:18-alpine AS build

## Mình đăng chạy folder nào, nếu ko có dòng này thì docker sẽ chạy trong file default
WORKDIR /app

## Copy các file từ trong máy của bạn vào docker img
COPY package*.json ./

## Copy local : Copy các thư mục vào trong img

# Install node package (muốn cài đặt những gi)
RUN npm install

RUN npm install -g @angular/cli

## Sao chép toàn bộ mã nguồn vào container
COPY . .

## Build ứng dung cho môi trường dự án
RUN ng build --configuration=production

# Sử dung nginx mới nhất
FROM nginx:latest

COPY --from=build app/dist/frontend-hospital/browser /usr/share/nginx/html

EXPOSE 80