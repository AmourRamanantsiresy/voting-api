# Voting api

You can use this API to create an voting app.

# Good to know

The admin will create a `vote` that will be wrap a list of vote section.
`Vote Section` will contains the list of all candidate to the concern section.

# Steps for admin

1. Create admin user:

   | **Method** | **Path**       | **Security** |
   | ---------- | -------------- | ------------ |
   | **POST**   | `/auth/signup` | `false`      |

   - **Body**

   ```json
   {
     "username": "john",
     "password": "12345678"
   }
   ```

2. Get token

   | **Method** | **Path**       | **Security** |
   | ---------- | -------------- | ------------ |
   | **POST**   | `/auth/signin` | `false`      |

   - **Body**

   ```json
   {
     "username": "john",
     "password": "12345678"
   }
   ```

3. Create vote

   | **Method** | **Path** | **Security** |
   | ---------- | -------- | ------------ |
   | **PUT**    | `/vote`  | `true`       |

   - **Body**

   ```json
   {
     "name": "VLF",
     "votersCountAllowed": 100
   }
   ```

4. Create vote section

   | **Method** | **Path**                     | **Security** |
   | ---------- | ---------------------------- | ------------ |
   | **PUT**    | `/vote/{voteId}/voteSection` | `true`       |

   - **Body**

   ```json
   {
     "name": "President",
     "voteCountAllowed": 1
   }
   ```

5. Create vote candidate

   | **Method** | **Path**                                 | **Security** |
   | ---------- | ---------------------------------------- | ------------ |
   | **PUT**    | `/voteSection/{voteSectionId}/candidate` | `true`       |

   - **Body**

   ```json
   [
     {
       "name": "Dan"
     },
     {
       "name": "Ben"
     }
   ]
   ```

# Steps for voters

1. Get vote list

   | **Method** | **Path** | **Security** |
   | ---------- | -------- | ------------ |
   | **GET**    | `/vote`  | `false`      |

2. Get vote section list

   | **Method** | **Path**                     | **Security** |
   | ---------- | ---------------------------- | ------------ |
   | **GET**    | `/vote/{voteId}/voteSection` | `false`      |

3. Get vote candidate list

   | **Method** | **Path**                                 | **Security** |
   | ---------- | ---------------------------------------- | ------------ |
   | **GET**    | `/voteSection/{voteSectionId}/candidate` | `false`      |

4. Vote

   | **Method** | **Path**              | **Security** |
   | ---------- | --------------------- | ------------ |
   | **PUT**    | `/vote/{voteId}/make` | `false`      |

   - **Body**

   ```json
   [
     {
       "sectionId": "7aca88de-6ea9-4748-95c8-a8533f8ecd81",
       "candidateIds": ["19e533d9-a373-40b1-80d8-e47663f621c6"]
     }
   ]
   ```

# Results

1. Get vote results

   | **Method** | **Path**                | **Security** |
   | ---------- | ----------------------- | ------------ |
   | **GET**    | `/vote/{voteId}/result` | `false`      |

   - **Response**

   ```json
   {
     "id": "7aca88de-6ea9-4748-95c8-a8533f8ecd81",
     "name": "VFL",
     "votersCount": "100",
     "createdAt": "2024-03-05T00:18:50.650796",
     "sectionResults": [
       {
         "id": "e87550bb-2f0a-4508-ac61-c2c61d12c66a",
         "name": "President",
         "voteCountAllowed": 1,
         "votersCount": 98,
         "whiteVoteCount": 2,
         "needSecondVote": false,
         "voteCandidateWinners": [
           {
             "name": "Luc",
             "id": "6b59d90e-de02-4a51-891a-50e9f5d5dcf4",
             "picture": null,
             "firstName": null,
             "lastName": null,
             "votes": 51
           }
         ],
         "voteCandidateResults": [
           {
             "name": "Luc",
             "id": "6b59d90e-de02-4a51-891a-50e9f5d5dcf4",
             "picture": null,
             "firstName": null,
             "lastName": null,
             "votes": 51
           },
           {
             "name": "Max",
             "id": "adc005d6-8cef-4edb-8d94-d9f76e325a43",
             "picture": null,
             "firstName": null,
             "lastName": null,
             "votes": 47
           }
         ]
       }
     ]
   }
   ```

# Next Vote

1. Create next vote

   If there is a vote section where no one of the candidates have got +50%, this endpoint will create new vote with the concerned vote section and with the winners of the same vote section

   | **Method** | **Path**              | **Security** |
   | ---------- | --------------------- | ------------ |
   | **PUT**    | `/vote/{voteId}/next` | `true`       |
