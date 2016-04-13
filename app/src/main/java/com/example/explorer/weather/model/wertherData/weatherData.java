package com.example.explorer.weather.model.wertherData;

import java.util.List;

/**
 * Created by explorer on 16-4-13.
 *
 */

public class weatherData {
    public Aqi aqi;
    public Basic basic;
    public List<dailyForecast> daily_forecast;
    public List<hourlyForecast> hourly_forecast;
    public Now now;
    public String status;
    public Suggestions suggestion;
}

/*
the class is from a json like this
{
    "HeWeather data service 3.0": [
        {
            "aqi": {
                "city": {
                    "aqi": "78",
                    "co": "1",
                    "no2": "38",
                    "o3": "169",
                    "pm10": "95",
                    "pm25": "57",
                    "qlty": "良",
                    "so2": "28"
                }
            },
            "basic": {
                "city": "杭州",
                "cnty": "中国",
                "id": "CN101210101",
                "lat": "30.319000",
                "lon": "120.165000",
                "update": {
                    "loc": "2016-04-13 15:51",
                    "utc": "2016-04-13 07:51"
                }
            },
            "daily_forecast": [
                {
                    "astro": {
                        "sr": "05:33",
                        "ss": "18:25"
                    },
                    "cond": {
                        "code_d": "300",
                        "code_n": "101",
                        "txt_d": "阵雨",
                        "txt_n": "多云"
                    },
                    "date": "2016-04-13",
                    "hum": "42",
                    "pcpn": "3.4",
                    "pop": "96",
                    "pres": "1006",
                    "tmp": {
                        "max": "25",
                        "min": "15"
                    },
                    "vis": "10",
                    "wind": {
                        "deg": "33",
                        "dir": "东北风",
                        "sc": "微风",
                        "spd": "1"
                    }
                },
                {
                    "astro": {
                        "sr": "05:32",
                        "ss": "18:26"
                    },
                    "cond": {
                        "code_d": "104",
                        "code_n": "300",
                        "txt_d": "阴",
                        "txt_n": "阵雨"
                    },
                    "date": "2016-04-14",
                    "hum": "38",
                    "pcpn": "0.0",
                    "pop": "27",
                    "pres": "1012",
                    "tmp": {
                        "max": "26",
                        "min": "16"
                    },
                    "vis": "10",
                    "wind": {
                        "deg": "90",
                        "dir": "北风",
                        "sc": "微风",
                        "spd": "6"
                    }
                },
                {
                    "astro": {
                        "sr": "05:31",
                        "ss": "18:26"
                    },
                    "cond": {
                        "code_d": "300",
                        "code_n": "305",
                        "txt_d": "阵雨",
                        "txt_n": "小雨"
                    },
                    "date": "2016-04-15",
                    "hum": "62",
                    "pcpn": "1.0",
                    "pop": "78",
                    "pres": "1012",
                    "tmp": {
                        "max": "21",
                        "min": "16"
                    },
                    "vis": "10",
                    "wind": {
                        "deg": "150",
                        "dir": "东风",
                        "sc": "微风",
                        "spd": "5"
                    }
                },
                {
                    "astro": {
                        "sr": "05:30",
                        "ss": "18:27"
                    },
                    "cond": {
                        "code_d": "305",
                        "code_n": "104",
                        "txt_d": "小雨",
                        "txt_n": "阴"
                    },
                    "date": "2016-04-16",
                    "hum": "86",
                    "pcpn": "11.2",
                    "pop": "75",
                    "pres": "1006",
                    "tmp": {
                        "max": "26",
                        "min": "15"
                    },
                    "vis": "7",
                    "wind": {
                        "deg": "302",
                        "dir": "东北风",
                        "sc": "微风",
                        "spd": "7"
                    }
                },
                {
                    "astro": {
                        "sr": "05:29",
                        "ss": "18:28"
                    },
                    "cond": {
                        "code_d": "300",
                        "code_n": "300",
                        "txt_d": "阵雨",
                        "txt_n": "阵雨"
                    },
                    "date": "2016-04-17",
                    "hum": "48",
                    "pcpn": "0.0",
                    "pop": "1",
                    "pres": "1017",
                    "tmp": {
                        "max": "22",
                        "min": "13"
                    },
                    "vis": "10",
                    "wind": {
                        "deg": "24",
                        "dir": "东北风",
                        "sc": "微风",
                        "spd": "2"
                    }
                },
                {
                    "astro": {
                        "sr": "05:28",
                        "ss": "18:28"
                    },
                    "cond": {
                        "code_d": "300",
                        "code_n": "104",
                        "txt_d": "阵雨",
                        "txt_n": "阴"
                    },
                    "date": "2016-04-18",
                    "hum": "64",
                    "pcpn": "0.1",
                    "pop": "53",
                    "pres": "1019",
                    "tmp": {
                        "max": "19",
                        "min": "13"
                    },
                    "vis": "10",
                    "wind": {
                        "deg": "128",
                        "dir": "西北风",
                        "sc": "3-4",
                        "spd": "13"
                    }
                },
                {
                    "astro": {
                        "sr": "05:27",
                        "ss": "18:29"
                    },
                    "cond": {
                        "code_d": "101",
                        "code_n": "101",
                        "txt_d": "多云",
                        "txt_n": "多云"
                    },
                    "date": "2016-04-19",
                    "hum": "59",
                    "pcpn": "0.0",
                    "pop": "25",
                    "pres": "1015",
                    "tmp": {
                        "max": "24",
                        "min": "12"
                    },
                    "vis": "10",
                    "wind": {
                        "deg": "171",
                        "dir": "东北风",
                        "sc": "微风",
                        "spd": "9"
                    }
                }
            ],
            "hourly_forecast": [
                {
                    "date": "2016-04-13 16:00",
                    "hum": "50",
                    "pop": "1",
                    "pres": "1006",
                    "tmp": "28",
                    "wind": {
                        "deg": "42",
                        "dir": "东北风",
                        "sc": "微风",
                        "spd": "12"
                    }
                },
                {
                    "date": "2016-04-13 19:00",
                    "hum": "68",
                    "pop": "0",
                    "pres": "1007",
                    "tmp": "26",
                    "wind": {
                        "deg": "77",
                        "dir": "东北风",
                        "sc": "微风",
                        "spd": "13"
                    }
                },
                {
                    "date": "2016-04-13 22:00",
                    "hum": "76",
                    "pop": "0",
                    "pres": "1010",
                    "tmp": "22",
                    "wind": {
                        "deg": "89",
                        "dir": "东风",
                        "sc": "微风",
                        "spd": "10"
                    }
                }
            ],
            "now": {
                "cond": {
                    "code": "101",
                    "txt": "多云"
                },
                "fl": "26",
                "hum": "49",
                "pcpn": "0",
                "pres": "1005",
                "tmp": "25",
                "vis": "7",
                "wind": {
                    "deg": "80",
                    "dir": "东南风",
                    "sc": "3-4",
                    "spd": "10"
                }
            },
            "status": "ok",
            "suggestion": {
                "comf": {
                    "brf": "较舒适",
                    "txt": "白天虽然有降雨，但会使人们感觉有些热，不过大部分人仍会有比较舒适的感觉。"
                },
                "cw": {
                    "brf": "不宜",
                    "txt": "不宜洗车，未来24小时内有雨，如果在此期间洗车，雨水和路上的泥水可能会再次弄脏您的爱车。"
                },
                "drsg": {
                    "brf": "舒适",
                    "txt": "建议着长袖T恤、衬衫加单裤等服装。年老体弱者宜着针织长袖衬衫、马甲和长裤。"
                },
                "flu": {
                    "brf": "少发",
                    "txt": "各项气象条件适宜，无明显降温过程，发生感冒机率较低。"
                },
                "sport": {
                    "brf": "较不宜",
                    "txt": "有降水，推荐您在室内进行健身休闲运动；若坚持户外运动，须注意保暖并携带雨具。"
                },
                "trav": {
                    "brf": "适宜",
                    "txt": "有降水，温度适宜，在细雨中游玩别有一番情调，可不要错过机会呦！但记得出门要携带雨具。"
                },
                "uv": {
                    "brf": "弱",
                    "txt": "紫外线强度较弱，建议出门前涂擦SPF在12-15之间、PA+的防晒护肤品。"
                }
            }
        }
    ]
}
 */
