package rmg.prsolution.dfm

import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.mock.BehaviorDelegate
import retrofit2.mock.MockRetrofit
import rmg.prsolution.dfm.domain.model.ChannelResponse
import rmg.prsolution.dfm.domain.model.Item
import rmg.prsolution.dfm.networking.ChannelApi

class MockChannelApi(mockRetrofit: MockRetrofit) : ChannelApi {

    override fun getChannelAsync(): Deferred<Response<ChannelResponse>> {
        val response = ChannelResponse(mockChannelResponse, 0, 0, 0, 0)
        return delegate.returningResponse(response).getChannelAsync()
    }

    private val delegate: BehaviorDelegate<ChannelApi> =
            mockRetrofit.create(ChannelApi::class.java)


    private val mockChannelResponse = listOf(
            Item(
                    description = "",
                    streamUrl = "https = //dfm-nrp.hostingradio.ru/nrp96.aacp",
                    id = "d2c664f1-9e12-4acf-9e0c-f1191f60115c",
                    apiUrl = "http = //playlist.rr.ru/cur_playing/station/DFM_NRP.json",
                    logoId = "c6502b58-5720-4f96-9fad-8cb173288ea7",
                    logoPath = "/uploads/ae/a2/58892223f94b65eb6cd4c6d4ba35.png",
                    lightText = false,
                    name = "DFM На рэпчике",
                    isLive = false,
                    order = 2,
                    dummyId = ""
            ),
            Item(
                    description = "Клубная и танцевальная поп-музыка",
                    streamUrl = "https://dfm.hostingradio.ru/dfm96.aacp",
                    id = "add441ad-4aaf-440f-9231-53a08eedd2eb",
                    apiUrl = "http://playlist.rr.ru/cur_playing/dfm/cur_playing.json",
                    logoId = "9e640a4a-cc1d-4473-ad30-8043da4a891e",
                    logoPath = "/uploads/c2/3e/5c4d06573d364b5720ea239c7033.png",
                    lightText = false,
                    name = "DFM 101,2",
                    isLive = false,
                    order = 1,
                    dummyId = ""

            ),
            Item(
                    description = "Западная клубная музыка",
                    streamUrl = "https://dfm-dfmclub.hostingradio.ru/dfmclub96.aacp",
                    id = "d936b97d-1626-4b3b-b4b4-1faae1baff56",
                    apiUrl = "http://playlist.rr.ru/cur_playing/station/ST01.json",
                    logoId = "7ec9f497-5332-4cdd-933e-5968ee486233",
                    logoPath = "/uploads/3b/d7/6748ae443a0a139942dd1051148f.jpg",
                    lightText = false,
                    name = "DFM Club",
                    isLive = false,
                    order = 3,
                    dummyId = ""

            ),
            Item(
                    description = "Русскоязычная танцевальная музыка",
                    streamUrl = "https://dfm-dfmrusdance.hostingradio.ru/dfmrusdance96.aacp",
                    id = "a79bd101-9be1-44cc-bd8c-732aab257381",
                    apiUrl = "http://playlist.rr.ru/cur_playing/station/ST03.json",
                    logoId = "0669ac1f-cd73-4b81-ae93-d70b53b9c799",
                    logoPath = "/uploads/b9/4c/7550882142a625d94cfd1d134b82.jpg",
                    lightText = false,
                    name = "DFM Russian Dance",
                    isLive = false,
                    order = 5,
                    dummyId = ""

            ),
            Item(
                    description = "Chill-out версии танцевальных хитов.",
                    streamUrl = "https://dfm-spoknochi.hostingradio.ru/spoknochi96.aacp",
                    id = "f18f24dc-c75e-4a29-9744-1eaf7c2bb055",
                    apiUrl = "http://playlist.rr.ru/cur_playing/station/ST05.json",
                    logoId = "6f1ddb07-4f41-4336-9c9f-b25ee064aee1",
                    logoPath = "/uploads/9e/12/1073581b25b4496b0c300ab2fd0b.jpg",
                    lightText = false,
                    name = "DFM Спокойной ночи, голыши!",
                    isLive = false,
                    order = 6,
                    dummyId = ""

            ),
            Item(
                    description = "Самые горячие хиты невероятно модного и востребованного сегодня стиля trap.",
                    streamUrl = "https://dfm-dfmtrap.hostingradio.ru/dfmtrap96.aacp",
                    id = "4f7075f5-55a8-4f74-be9b-e0d8b3619ce8",
                    apiUrl = "http://playlist.rr.ru/cur_playing/station/ST07.json",
                    logoId = "717ae93b-0d36-4fcd-81fc-f8a367f7cf4b",
                    logoPath = "/uploads/85/f0/f0ed9d162eed7f6e378ec88dcaf5.jpg",
                    lightText = false,
                    name = "DFM Trap",
                    isLive = false,
                    order = 4,
                    dummyId = ""

            ),
            Item(
                    description = "Танцевальные хиты первой половины 2000-х годов.",
                    streamUrl = "https://dfm-dfmdinamit.hostingradio.ru/dfmdinamit96.aacp",
                    id = "3da41cd5-b932-4e2f-b350-ac8ffc534a3a",
                    apiUrl = "http://playlist.rr.ru/cur_playing/station/ST16.json",
                    logoId = "3d224ecb-8876-4dd4-a2dc-432a8e38389f",
                    logoPath = "/uploads/94/8d/282a8b611dc8551050539e429469.jpg",
                    lightText = false,
                    name = "DFM Динамит",
                    isLive = false,
                    order = 8,
                    dummyId = ""

            ),
            Item(
                    description = "Хиты умопомрачительных, сумасшедших и непредсказуемых  90-х.",
                    streamUrl = "https://dfm-disc90.hostingradio.ru/disc9096.aacp",
                    id = "e677eeb8-b226-44f7-b252-ca7032af3dba",
                    apiUrl = "http://playlist.rr.ru/cur_playing/station/st18.json",
                    logoId = "19217cf6-cc27-4b94-8cd7-876994e6b701",
                    logoPath = "/uploads/8e/64/c3528fa5aa4a9cdbfdd303c619a1.jpg",
                    lightText = false,
                    name = "Дискач 90-х",
                    isLive = false,
                    order = 7,
                    dummyId = ""
            ),
            Item(
                    description = "Музыка в стиле Deep House.",
                    streamUrl = "https://dfm-dfmdeep.hostingradio.ru/dfmdeep96.aacp",
                    id = "d936b97d-1626-4b3b-b4b4-1faae1baff55",
                    apiUrl = "http://playlist.rr.ru/cur_playing/station/ST24.json",
                    logoId = "110d10be-78e5-4c7c-b301-b9430efcbddd",
                    logoPath = "/uploads/ef/31/bc1a10caa7b2c56af9ae7ddb4958.jpg",
                    lightText = false,
                    name = "DFM Deep",
                    isLive = false,
                    order = 2,
                    dummyId = ""
            )
    )
}
