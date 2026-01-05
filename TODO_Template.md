# TODO List 项目说明文档（示例模板）

## 1. 技术选型
- **编程语言**：后端采用 Node.js，依托其非阻塞 I/O 特性提升 API 处理效率，搭配 IntelliJ IDEA 作为开发工具（优势：强大的代码提示、断点调试和项目管理功能，支持 Node.js 环境快速配置）；前端使用 JavaScript + TypeScript（Vue3 推荐组合），TypeScript 提供静态类型检查，减少前端逻辑错误。  
- **框架/库**：后端基于 Express 框架（轻量灵活，适合快速搭建 RESTful 接口）；前端采用 Vue3（Composition API 便于逻辑复用，响应式系统更高效）+ Vue Router（路由管理）+ Pinia（状态管理，替代 Vuex 更简洁）。
- **数据库/存储**：选用 MySQL，理由：任务数据（标题、状态、截止时间等）具有明确结构化特征，MySQL 的事务支持和索引优化能保障数据一致性与查询效率。
- 替代方案对比：前端未用 React 是考虑到对 Vue 生态更熟悉，开发效率更高。

## 2. 项目结构设计
- 采用前后端分离模式，前端通过 Axios 与后端 API 通信，后端负责数据校验、业务逻辑处理及与 MySQL 交互，实现数据持久化。
  - 目录结构示例：  
    ```
    后端（IDEA项目结构）
    src/
      main/
        java/
          config/         #配置文件（数据库连接，端口设置）
          controller/     #业务逻辑
          dto/
          entity/
          repository/
          service/
          todolist/
    前端（Vue项目结构）
    src/
      api/                #API封装
      assets/
      components/         #通用组件（任务，表单）
      router/             #路由配置（页面跳转）
      store/              #Pinia（管理任务数据）
      view/               #页面视图
    vite.config.ts
    ```  
- 模块职责说明。
  - 后端 models：通过 Sequelize ORM 定义 Task 模型，映射 MySQL 表字段（id、title、status、deadline 等）； 
  - 前端 api 模块：统一封装 GET/POST 等请求方法，避免组件中重复编写 Axios 逻辑； 
  - 后端 middleware：请求日志中间件记录接口访问时间和参数，便于调试。
## 3. 需求细节与决策
- 描述是否必填？如何处理空输入？
  - 任务标题为必填项，前端通过 Vue3 的 v-model 结合自定义校验函数（如 title.length > 0）提示 “请输入任务标题”，后端接口层通过 if (!title) return res.status(400).send('标题不能为空') 二次拦截。
- 已完成的任务在 UI 或 CLI 中如何显示？ 
  - 前端通过动态类名实现样式区分，完成状态添加灰色背景和删除线样式。 
- 任务排序逻辑（默认按创建时间，用户可选按优先级）。
  - 默认按创建时间升序（最早创建在前），用户可通过下拉框切换为 “截止时间倒序”（即将到期在前），排序逻辑在前端通过 Array.sort() 实现，避免频繁请求后端。 
- 如果涉及扩展功能（例如同步/提醒），简述设计思路。  

## 4. AI 使用说明
- 是否使用 AI 工具？（ChatGPT / Copilot / Cursor / 其他） 
  - ChatGPT、Gemini
- 使用 AI 的环节：  
  - 代码片段生成：后端模型定义：GPT基于字段注释自动生成模型关联代码;前端 Vue3 组件逻辑用Gemini辅助编写响应逻辑。
  - Bug 定位  
  - 文档初稿编写  
- AI 输出如何修改：例如“AI 给出的方案用了 localStorage，我改成了 IndexedDB 以支持更复杂数据”。  

## 5. 运行与测试方式
- 本地运行方式（安装依赖、启动命令）。  
- 已测试过的环境（例如 Node.js v20，macOS）。  
- 已知问题与不足。  

## 6. 总结与反思
- 如果有更多时间，你会如何改进？  
- 你觉得这个实现的最大亮点是什么？  
