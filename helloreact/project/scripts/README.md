\> .\node_modules\.bin\babel --presets @babel/preset-react,@babel/preset-env helloreact/project/js/source -d helloreact/project/js/build

\> browserify js/build/app.js -o bundle.js

$ cat css/*/* css/*.css | sed 's/..\/..\/images/images/g' > bundle.css