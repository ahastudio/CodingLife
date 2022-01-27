Rails.application.routes.draw do
  root 'welcome#index'

  resources :jobs do
    resources :frames
  end

  resources :tickets
end
