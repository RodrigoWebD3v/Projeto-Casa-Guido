// Função para tratar valores padrão
const getDefaultValue = (value, defaultValue) => {
   if (value === null || value === undefined) {
      return defaultValue;
   }
   return value;
};

// Função para tratar arrays
const getDefaultArray = (value) => {
   if (value === null || value === undefined || !Array.isArray(value)) {
      return [];
   }
   return value;
};

// Função para tratar objetos
const getDefaultObject = (value, defaultObject = {}) => {
   if (value === null || value === undefined || typeof value !== 'object' || Array.isArray(value)) {
      return defaultObject;
   }
   return value;
};

// Função para tratar strings
const getDefaultString = (value, defaultValue = "") => {
   if (value === null || value === undefined || value === "") {
      return defaultValue;
   }
   return value;
};

// Função para tratar números
const getDefaultNumber = (value, defaultValue = 0) => {
   if (value === null || value === undefined || isNaN(value)) {
      return defaultValue;
   }
   return value;
};

// Função para tratar booleanos
const getDefaultBoolean = (value, defaultValue = false) => {
   if (value === null || value === undefined) {
      return defaultValue;
   }
   return Boolean(value);
};

module.exports = {
   getDefaultValue,
   getDefaultArray,
   getDefaultObject,
   getDefaultString,
   getDefaultNumber,
   getDefaultBoolean
}; 