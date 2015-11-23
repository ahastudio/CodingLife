export APP_PATH=osx/Demo.app/Contents/Resources/app
coffee -c main.coffee
mkdir -p $APP_PATH
cp package.json $APP_PATH
cp -r node_modules $APP_PATH
cp main.js $APP_PATH
cp index.html $APP_PATH
cp app.ts $APP_PATH
cp style.css $APP_PATH
