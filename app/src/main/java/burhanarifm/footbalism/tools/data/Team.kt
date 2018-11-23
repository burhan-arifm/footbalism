package burhanarifm.footbalism.tools.data

import com.google.gson.annotations.SerializedName

data class Team (
    @SerializedName("idTeam")
    var teamId : String? = null,

    @SerializedName("strTeam")
    var teamName : String? = null,

    @SerializedName("strManager")
    var teamManager : String? = null,

    @SerializedName("strStadium")
    var teamStadium : String? = null,

    @SerializedName("strTeamBadge")
    var teamBadge : String? = null,

    @SerializedName("strDescriptionEN")
    var teamDescription : String? = null
)