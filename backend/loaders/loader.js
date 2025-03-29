//loader.js

const authMiddleware = require("../middlewares/authMiddleware");

const express = require("express");
const cors = require("cors");
const routes = require("../routes/routes");
const authRoutes = require("../routes/authRoutes");

const init = (app) => {
  app.use(cors());
  app.use(express.json());

  app.use("/api/v1/auth", authRoutes);
  app.use("/api/v1/", authMiddleware);
  app.use("/api/v1/", routes);
 
};

module.exports = { init };