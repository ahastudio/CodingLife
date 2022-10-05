module.exports = {
  transform: {
    '^.+\\.js$': '@swc/jest',
  },
  testPathIgnorePatterns: [
    '<rootDir>/node_modules/',
  ],
};
