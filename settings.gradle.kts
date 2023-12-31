rootProject.name = "keyboard-maniac-sns"

include("common")
include("domain")
include("usecase")

// infrastructure
include(":infrastructure-persistence")
project(":infrastructure-persistence").projectDir = file("infrastructure/persistence")

include(":infrastructure-http")
project(":infrastructure-http").projectDir = file("infrastructure/http")

include(":infrastructure-redis")
project(":infrastructure-redis").projectDir = file("infrastructure/redis")

// bootstrap
include(":bootstrap-api")
project(":bootstrap-api").projectDir = file("bootstrap/api")

include(":bootstrap-batch")
project(":bootstrap-batch").projectDir = file("bootstrap/batch")
