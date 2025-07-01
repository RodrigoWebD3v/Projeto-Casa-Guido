const express = require("express");
const { init } = require('../backend/loaders/loader');

const app = express();

init(app);

module.exports = app;