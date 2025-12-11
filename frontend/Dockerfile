# 1. Use Node.js to run the app
FROM node:18-alpine

# 2. Set the working directory inside the container
WORKDIR /app

# 3. Copy package manager files first (for better caching)
COPY package.json package-lock.json ./

# 4. Install dependencies
RUN npm install

# 5. Copy the rest of the source code
COPY . .

# 6. Expose the port React runs on
EXPOSE 3000

# 7. Start the application
CMD ["npm", "start"]