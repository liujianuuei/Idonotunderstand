@echo off

REM cleanup last version
if exist _deployme rd /s /q _deployme
md _deployme

REM build
call scripts\build.cmd

REM minify js
echo uglifyjs compressing...
call ..\..\node_modules\.bin\uglifyjs bundle.js -o _deployme\bundle.js
echo uglifyjs compressing... DONE

REM minify css
echo cssshrink compressing...
call ..\..\node_modules\.bin\cssshrink bundle.css > _deployme\bundle.css
echo cssshrink compressing... DONE

REM copy html and images
echo copying html and images...
copy index.html _deployme\index.html
xcopy /s /e images _deployme\images\
echo copying... DONE

REM Done
echo Deploy DONE %date%%time%

@echo on