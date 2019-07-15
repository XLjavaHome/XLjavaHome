@echo for /f "skip=3 tokens=4" %%i in ('sc query "VMAuthdService"') do echo %%i
