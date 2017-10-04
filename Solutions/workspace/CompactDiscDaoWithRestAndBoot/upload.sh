#!/bin/bash

scp -i ~/ConygreAWSKey.pem target/CompactDiscRestDataBoot-0.0.1-SNAPSHOT.jar ec2-user@assessments.conygre.com:~

ssh -i ConygreAWSKey.pem ec2-user@assessments.conygre.com

