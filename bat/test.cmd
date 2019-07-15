for /f %%i in (a.txt) do echo %%i
for /f "delims= " %%i in (a.txt) do echo %%i
