# jin

初始项目骨架：Spring Boot（后端） + Vue 3（前端）

目标：购物为主、展示附近美食/商家并支持收藏；三角色（USER/MERCHANT/ADMIN）。

本分支 feat/init-skeleton 包含可在 IDEA 中运行的后端基础骨架与前端基础骨架，下一步我会逐步实现权限、JWT、地图、购物、收藏等模块。

快速启动（后端）
1. 在本地 MySQL 中创建数据库：

   CREATE DATABASE jin_db CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;

2. 修改数据库连接（默认使用 root/123456）：
   - backend/src/main/resources/application.yml

3. 使用 IntelliJ IDEA 打开 backend 目录为 Maven 项目，运行 JinApplication（主类）或执行：
   mvn spring-boot:run

快速启动（前端）
1. 进入 frontend 目录：
   cd frontend
2. 安装依赖并运行：
   npm install
   npm run dev

后续我将：
- 实现 JWT 鉴权、三角色权限控制、商品/商家/收藏/购物车/下单流程、地图展示附近商家
- 提交数据库初始化脚本与 Postman 集合


---

如果你确认没问题，我会在接下来的提交中实现完整用户认证与核心业务。