# RuneBot-OCR

## Download
JAR: [[KotlinTest-1.0-SNAPSHOT.jar](https://github.com/KaiBurton/RuneBot-OCR/blob/main/build/libs/KotlinTest-1.0-SNAPSHOT.jar)]

## Requirements

- [Discord](https://discord.gg) web or desktop app.
- Virtual Machine that has Discord open at all times.
- [RuneBot Discord](https://discord.gg/Pk9M3TKFzF) -> `runebot-commands` channel selected.
- Your own API key from https://ocr.space, you can obtain one for free and replace the current key within ```runebot.ocr.misc.API -> postData.put("apikey", "YOUR_KEY")```.

## Usage
**cmd.exe/PowerShell.exe** -> `java -jar KotlinTest-1.0-SNAPSHOT.jar`

Currently, you can change the Event you wish to do my modifying the booleans within ```runebot.ocr.Constants``` 
> (This will be updated to a config file & command-line argument)
![image](https://user-images.githubusercontent.com/26250917/122693021-ed368080-d22f-11eb-9157-9f0f74e6adfb.png)

## What does the TriviaEvent do?

1. Takes a screenshot of the screen, uses an OCR API and converts the screenshot to text.
2. Executes commands such as /daily (every 6 hours) and answers these questions automatically.
3. Extracts words from the OCR text and compares to a dumped list of questions & answers.
4. Compares the two questions together and the one that matches the most, that answer will be picked.
5. Completed. Runs again every 6 hours.

![image](https://user-images.githubusercontent.com/26250917/122553996-4afc7a00-d030-11eb-99c3-79b6eabf9c7a.png)
