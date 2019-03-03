'use strict';

var _react = _interopRequireDefault(require("react"));

var _reactDom = _interopRequireDefault(require("react-dom"));

var _Logo = _interopRequireDefault(require("./components/Logo"));

function _interopRequireDefault(obj) { return obj && obj.__esModule ? obj : { default: obj }; }

_reactDom.default.render(_react.default.createElement("h1", null, _react.default.createElement(_Logo.default, null), " Welcome to The App!"), document.getElementById('app'));