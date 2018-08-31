import Foundation

struct Music {
    var startTime: String
    var endTime: String
    var title: String
    var melody: [String]
    var playedMelody: [String]?
}

extension Music {
    mutating func updatePlayedMelody(melody: [String]) -> Music {
        return Music(startTime: self.startTime, endTime: self.endTime,
                     title: self.title, melody: self.melody, playedMelody: melody)
    }
}

func serializeMusic(musicInfos: [String]) -> [Music] {

    return musicInfos.compactMap {
        let strArr = $0.split(separator: ",")

        return Music(startTime: String(strArr[0]), endTime: String(strArr[1]),
                     title: String(strArr[2]), melody: getMelodyArr(melody: String(strArr[3])), playedMelody: nil)
    }
}

func getMelodyArr(melody: String) -> [String] {
    var result = [String]()
    for char in melody {
        if char == "#" {
            let lastItem = result.removeLast()
            result.append(lastItem.lowercased())
        } else {
            result.append(String(char))
        }
    }
    return result
}

func getTotalPlayTime(music: Music) -> Int {

    let startTime = music.startTime.split(separator: ":")
    let endTime = music.endTime.split(separator: ":")

    let startHour = Int(String(startTime.first!))!
    let startMin = Int(String(startTime.last!))!

    let endHour = Int(String(endTime.first!))!
    let endMin = Int(String(endTime.last!))!

    let hour = endHour - startHour
    let minute = endMin - startMin

    return hour * 60 + minute
}

func getPlayedMelody(music: Music) -> [String] {

    let playTime = getTotalPlayTime(music: music)

    let melodyLength = music.melody.count

    let playedMelodyCount = playTime / melodyLength
    let extraMelodyCount = playTime % melodyLength

    var extra = [String]()
    for (idx, item) in music.melody.enumerated() {
        if idx < extraMelodyCount {
            extra.append(item)
        }
    }

    if playedMelodyCount > 0 {
        let resultA: [String] = (1...playedMelodyCount).reduce([], { acc, cur in
            return acc + music.melody
        })

        return resultA + extra
    }
    return extra
}

func updatePlayedMelody(musics: [Music]) -> [Music] {
    return musics.map {
        var music = $0
        let playedMelody = getPlayedMelody(music: $0)
        return music.updatePlayedMelody(melody: playedMelody)
    }
}

func hasMelody(melody: [String], music: Music) -> Bool {
    let melodyStr = melody.reduce("") { acc, cur in
        return acc + cur
    }
    let playedMusicStr = music.playedMelody!.reduce("") { acc, cur in
        return acc + cur
    }

    return playedMusicStr.contains(melodyStr)
}

func solution(_ m:String, _ musicinfos:[String]) -> String {
    let musics = serializeMusic(musicInfos: musicinfos)
    let updatedMusic = updatePlayedMelody(musics: musics)

    let listenedMelodyArr = getMelodyArr(melody: m)

    let result = updatedMusic.filter {
        return hasMelody(melody: listenedMelodyArr, music: $0)
    }
    if result.count > 0 {
        if result.count == 1 {
            return result.first!.title
        }
        return getMaxLengthResult(musics: result).title
    }
    return "(None)"
}

func getMaxLengthResult(musics: [Music]) -> Music {
    print(musics.count)
    var resultA = musics.first!
    var playtime = getTotalPlayTime(music: resultA)

    musics.dropFirst().forEach {
        if getTotalPlayTime(music: $0) > playtime {
            resultA = $0
            playtime = getTotalPlayTime(music: $0)
        }
    }
    return resultA
}

let listenedMelody1 = "ABCDEFG"
let listenedMelody2 = "CC#BCC#BCC#BCC#B"
let listenedMelody3 = "ABC"

let testA = ["10:19,12:16,HELLO,CDEFGAB", "10:19,12:16,HOOOb1,CDEFGABCDEFG",
             "10:19,12:16,WORLD,ABCDEF", "10:19,12:16,HOOOb2,CDEFGAB"]

let testB = ["03:00,03:30,FOO,CC#B", "04:00,04:08,BAR,CC#BCC#BCC#B"]
let testC = ["12:00,12:59,HELLO,C#DEFGABCDEF", "13:00,13:05,WORLD,ABCDEF"]

print(solution(listenedMelody1, testA))
print(solution(listenedMelody2, testB))
print(solution(listenedMelody3, testC))
