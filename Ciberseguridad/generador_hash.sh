#!/bin/bash

ls -l /bin/ | cut -d " " -f9 | xargs -I ARG sha256sum /bin/ARG > SHA.out
