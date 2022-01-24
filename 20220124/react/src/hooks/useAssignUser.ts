import { gql } from 'graphql-request';

import client from '../client';

const CHANGE_TICKET_ASSIGNEE = gql`
  mutation ChangeTicketAssignee($id: Int, $userId: Int) {
    changeTicketAssignee(id: $id, userId: $userId) {
      id
      assignee {
        user {
          id
          name
          avatar {
            imageUrl
          }
        }
      }
    }
  }
`;

export default function useAssignUser() {
  return {
    async assignUser({ ticketId, userId }: {
      ticketId: number;
      userId: number | null;
    }): Promise<any> {
      const data = await client.request(CHANGE_TICKET_ASSIGNEE, {
        id: ticketId,
        userId,
      });
      return data.changeTicketAssignee;
    },
  };
}
